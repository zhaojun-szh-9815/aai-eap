import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import { Table, Header, HeaderRow, HeaderCell, Body, Row, Cell } from '@table-library/react-table-library';
import {
  useRowSelect,
} from "@table-library/react-table-library/select";
import { usePagination } from '@table-library/react-table-library/pagination'
import { useTheme } from '@table-library/react-table-library/theme';
import Select from 'react-select';
import Detail from './Details';
import { QUERY_LIMIT } from './Constants';

const DynamicTable = ({ apiUrl, dataSample }) => {
  // store data with format {nodes: [], pageInfo: []} for using in react-table-library
  const [data, setData] = useState({nodes: [], pageInfo: {}});
  // isloading to check if data is empty, prevent crash
  // const [isLoading, setIsLoading] = useState(true);
  // columns using dataSample to generate columns
  const [columns, setColumns] = useState([])
  // selectedOption is used in search, it will select a column to search
  const [selectedOption, setSelectedOption] = useState({value: null, label: null})
  // options are generated from columns with format [{value:null, label:null}] to match Select component
  const [options, setOptions] = useState([])
  // search is keywords to search
  const [search, setSearch] = React.useState("");
  // tableData is the filtered data, it will show in table
  const tableData = {
    nodes: data.nodes.filter((item) => {
      // console.log("filter by:", selectedOption.value)
      // console.log("item:", String(item[selectedOption.value]).toLowerCase())
      // console.log("search:", search.toLowerCase())
      return String(item[selectedOption.value]).toLowerCase().includes(search.toLowerCase())
    })
  }

  // selectedRow is used in row select, when select a row, it will store the row informations as an object
  const [selectedRow, setSelectedRow] = useState(null);
  // when select or add, isDetailOpen controlls the detail component
  const [isDetailOpen, setIsDetailOpen] = useState(false);
  // if isAdding, show the empty entry for input
  const [isAdding, setIsAdding] = useState(false);

  // for file import
  const fileInputRef = useRef(null);

  const handleSearch = (event) => {
    setSearch(event.target.value);
  };

  const select = useRowSelect(tableData, {
    onChange: onSelectChange
  });

  function onSelectChange(action, state) {
    console.log("onSelectChange: ", action, state);
    if (state !== null && state.id !== null) {
      console.log("detail id:", state.id)
      setSelectedRow(data.nodes.find(obj => obj.id === state.id))
      setIsDetailOpen(true)
    } else {
      console.log("detail:", state)
    }
  }

  // Function to open modal for adding a new row
  const handleAddRow = () => {
    setIsAdding(true);
    setSelectedRow(dataSample);
    setIsDetailOpen(true);
  };

  const saveRow = async (editedRow) => {
    if (isAdding) {
      setIsAdding(false)
    }
    await axios.post(apiUrl+"/save", editedRow).then((response) => console.log(response.data)).catch((error) => console.error(error))
    setIsDetailOpen(false);
    fetchData(0)
  };

  const deleteRow = async (editedRow) => {
    await axios.delete(apiUrl+"/remove/" + editedRow.id).then((response) => console.log(response)).catch((error) => console.error(error))
    setIsDetailOpen(false)
    fetchData(0)
    console.log("after delete: ", data)
  }

  const fetchData = React.useCallback(async (pageIndex) => {
    try {
      const response = await axios.get(apiUrl+"/list/"+pageIndex);
      console.log("fetch:",response.data)
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  },[]);

  useEffect(() => {
    fetchData(0);
    setColumns(getAllKeys(dataSample))
    setOptions(getAllKeys(dataSample).map((item, index) => ({value: item, label: item})))
  }, [apiUrl, dataSample]);

  const pagination = usePagination(
    data, {
      state: {
        page: 0,
        size: QUERY_LIMIT,
      },
      onChange: onPaginationChange
    },
    {
      isServer: true
    }
  )

  function onPaginationChange(action, state) {
    fetchData(state.page)
  }

  const getAllKeys = (data) => {
      return Object.keys(data)
  };

  const handleButtonClick = () => {
    fileInputRef.current.click(); // Trigger file input click on button click
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0]; // Get selected file
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file); // Prepare file for upload

    axios.post(apiUrl+"/upload", formData, {
      headers: {
        'Content-Type': 'multipart/form-data', // Important for files
      },
    })
    .then(response => {
      console.log(response.data);
      // Handle success response
      fetchData(0)
    })
    .catch(error => {
      console.error(error);
      // Handle error response
    });
  };

  const theme = useTheme({
    HeaderRow: `
        .th {
          border-bottom: 1px solid #a0a8ae;
          
        }
      `,
    BaseCell: `
        &:not(:last-of-type) {
          border-right: 1px solid #a0a8ae;
        }

        text-align: center;
        padding: 8px 8px;
      `,
    Table: `
        --data-table-library_grid-template-columns:  repeat(21, 10%);
      `,
  });

  return (
    
    <div style={{ display: 'flex', flexDirection: 'column', width: '95%' }}>
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', gap: '10px' }}>
          Search by
          <label style={{width: '20%', zIndex: 1001}}><Select defaultValue={selectedOption} onChange={setSelectedOption} options={options}/></label>:&nbsp;
          <input id="search" type="text" value={search} onChange={handleSearch} />
        <br />

        <button onClick={handleAddRow}>Add Row</button>
        <input
          type="file"
          ref={fileInputRef}
          onChange={handleFileChange}
          style={{ display: 'none' }} // Hide file input
        />
        <button onClick={handleButtonClick}>Import</button>
      </div>

      <div style={{ marginTop: '10px' }}>
      <Table
          data={tableData}
          theme={theme}
          select={select}
          pagination={pagination}
          layout={{custom:true,horizontalScroll:true}}
      >
          {
              (tableList) => (
                  <>
                      <Header>
                          <HeaderRow>
                              {
                                  columns.map((value, index) => {
                                      return <HeaderCell key={value}>{value}</HeaderCell>
                                  })
                              }
                          </HeaderRow>
                      </Header>

                      <Body>
                          {tableList.map((item) => (
                              <Row key={item.id} item={item}>
                                  {
                                      columns.map((value, index) => {
                                          if (typeof item[value] === 'object' && !Array.isArray(item[value]) && item[value] !== null) {
                                              return <Cell key={value}>{item[value].name}</Cell>
                                          } else {
                                              return <Cell key={value}>{item[value]}</Cell>
                                          }
                                      })
                                  }
                              </Row>
                          ))}
                      </Body>
                  </>
              )
          }
      </Table>
      </div>
      <div style={{ marginTop: '10px' }}>
      {data.pageInfo && (
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
            }}
          >
            <span>Total Pages: {data.pageInfo.totalPages}</span>

            <span>
              Page:{" "}
              {Array(data.pageInfo.totalPages)
                .fill()
                .map((_, index) => (
                  <button
                    key={index}
                    type="button"
                    style={{
                      fontWeight:
                        pagination.state.page === index ? "bold" : "normal",
                    }}
                    onClick={() => pagination.fns.onSetPage(index)}
                  >
                    {index + 1}
                  </button>
                ))}
            </span>
          </div>
        )}
        </div>

      {isDetailOpen && (
          <Detail
            isOpen={isDetailOpen}
            isAdd={isAdding}
            onClose={() => {
              setIsDetailOpen(false)
              setIsAdding(false)
            }}
            onSave={(editedRow) => {
              saveRow(editedRow)
            }}
            onDelete={deleteRow}
            data={selectedRow}
          />
        )}
    </div>
  );
};

export default DynamicTable;
