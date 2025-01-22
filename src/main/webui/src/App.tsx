import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";

import { useGetHomeassistantCoreInfo } from "../openapi/queries";

function App() {
  const { data, isLoading, isError } = useGetHomeassistantCoreInfo();

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Cybersecurity Awareness Dashboard</h1>
      <div className="card">
        {isLoading && <p>Loading...</p>}
        {isError && <p>Error!</p>}
        {data && (
          <div>
            <p>
              Home Assistant {data.version} running on {data.machine}/
              {data.arch}
            </p>
            <p>
              IP: {data.ip_address}, Port: {data.port}, SSL: {data.ssl ? "Yes" : "No"}
            </p>
            {data.update_available && (
              <p>Update to {data.version_latest} is available!</p>
            )}
          </div>
        )}
      </div>
    </>
  );
}

export default App;
