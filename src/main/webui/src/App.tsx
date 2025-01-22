import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";

import { CoreInfoDto } from "../openapi/requests";
import { useQuery } from "@tanstack/react-query";

function App() {
  const baseUrl =
    window.location.protocol +
    "//" +
    window.location.host +
    window.location.pathname.substring(1);

  const { data, isLoading, isError } = useQuery<CoreInfoDto>({
    queryKey: ["core", "info"],
    queryFn: async () => {
      const response = await fetch(baseUrl + "/core/info");

      if (!response.ok) throw new Error("Error while fetching core info");

      return response.json();
    },
  });

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
              IP: {data.ip_address}, Port: {data.port}, SSL:{" "}
              {data.ssl ? "Yes" : "No"}
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
