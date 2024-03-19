import DynamicTable  from "./DynamicTable";
import {RECORD_MAINTENANCE_APIURL, RECORD_MAINTENANCE_SAMPLE} from "./Constants"

function App() {
  return (
    <div style={{
      display: 'flex', alignItems: 'center', justifyContent: 'center'
    }}>
      <DynamicTable apiUrl={RECORD_MAINTENANCE_APIURL} dataSample={RECORD_MAINTENANCE_SAMPLE}/>
    </div>
  );
}

export default App;
