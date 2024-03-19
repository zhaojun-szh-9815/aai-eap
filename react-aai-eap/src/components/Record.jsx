import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Table,
  Header,
  HeaderRow,
  Body,
  Row,
  HeaderCell,
  Cell,
} from "@table-library/react-table-library/table";
import { useTheme } from '@table-library/react-table-library/theme';

const Component = () => {
  const [data, setData] = React.useState();
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://192.168.0.238:8080/record/maintenance/");
        if (response.data.length > 0) {
          console.log(response.data)
            setData({nodes: response.data});
            setIsLoading(false)
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const handleUpdate = (value, id, property) => {
    setData((state) => ({
      ...state,
      nodes: state.nodes.map((node) => {
        if (node.id === id) {
          
          return { ...node, [property]: value };
        } else {
          return node;
        }
      }),
    }));
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
        --data-table-library_grid-template-columns:  repeat(21, 25%);
        width: 80%;
        display: 'flex';
        align-items: 'center';
        justify-content: 'center'
      `,
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <Table data={data}
    theme={theme}
    layout={{custom:true,horizontalScroll:true}}
    >
      {(tableList) => (
        <>
          <Header>
            <HeaderRow>
              <HeaderCell>#</HeaderCell>
              <HeaderCell>Task Name</HeaderCell>
              <HeaderCell>Task Description</HeaderCell>
              <HeaderCell>Ticket Info</HeaderCell>
              <HeaderCell>Client</HeaderCell>
              <HeaderCell>Google Drive Sub Folder Name</HeaderCell>
              <HeaderCell>Google Drive Folder Link</HeaderCell>
              <HeaderCell>IDC Site</HeaderCell>
              <HeaderCell>Quantity</HeaderCell>
              <HeaderCell>Device Specification</HeaderCell>
              <HeaderCell>Part Number</HeaderCell>
              <HeaderCell>Defective Serial Number</HeaderCell>
              <HeaderCell>Task Assigned Date</HeaderCell>
              <HeaderCell>Start Date</HeaderCell>
              <HeaderCell>Complete Date</HeaderCell>
              <HeaderCell>Client PM</HeaderCell>
              <HeaderCell>AAI PM</HeaderCell>
              <HeaderCell>AAI onsite techinician</HeaderCell>
              <HeaderCell>onsite working hours</HeaderCell>
              <HeaderCell>Onsite technician dispatched</HeaderCell>
              <HeaderCell>Comments</HeaderCell>
            </HeaderRow>
          </Header>

          <Body>
            {tableList.map((item) => (
              <Row key={item.id} item={item}>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.id}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "id")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.taskName}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "taskName")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.taskDescription}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "taskDescription")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.ticketInfo}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "ticketInfo")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.client}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "client")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.gdFolderName}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "gdFolderName")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.gdFolderLink}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "gdFolderLink")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.idcSite.name}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "idcSite")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="number"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.quantity}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "quantity")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.deviceSpecification}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "deviceSpecification")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.partNumber}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "partNumber")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.defectiveSerialNumber}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "defectiveSerialNumber")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="date"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={new Date(item.taskAssignedDate).toISOString().substr(0, 10)}
                    onChange={(event) =>
                      handleUpdate(
                        new Date(event.target.value),
                        item.id,
                        "taskAssignedDate"
                      )
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="date"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={new Date(item.startDate).toISOString().substr(0, 10)}
                    onChange={(event) =>
                      handleUpdate(
                        new Date(event.target.value),
                        item.id,
                        "startDate"
                      )
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="date"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={new Date(item.endDate).toISOString().substr(0, 10)}
                    onChange={(event) =>
                      handleUpdate(
                        new Date(event.target.value),
                        item.id,
                        "endDate"
                      )
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.clientPM}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "clientPM")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.aaiPM.name}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "aaiPM")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.aaiOnsiteTechnician.name}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "aaiOnsiteTechnician")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="number"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.onsiteWorkingHours}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "onsiteWorkingHours")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="number"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.onsiteTechnicianDispatched}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "onsiteTechnicianDispatched")
                    }
                  />
                </Cell>
                <Cell>
                  <input
                    type="text"
                    style={{
                      width: "100%",
                      border: "none",
                      fontSize: "1rem",
                      padding: 0,
                      margin: 0,
                      textAlign: 'center'
                    }}
                    value={item.comments}
                    onChange={(event) =>
                      handleUpdate(event.target.value, item.id, "comments")
                    }
                  />
                </Cell>
              </Row>
            ))}
          </Body>
        </>
      )}
    </Table>
  );
};

export default Component;
