import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";

import { ResultDtoCoreInfoDto } from "../openapi/requests";
import { useQuery } from "@tanstack/react-query";

function App() {
  const baseUrl =
    window.location.protocol +
    "//" +
    window.location.host +
    window.location.pathname.substring(1);

  const { data, isLoading, isError } = useQuery<ResultDtoCoreInfoDto>({
    queryKey: ["core", "info"],
    queryFn: async () => {
      const url = baseUrl + "/api/home-assistant/core/info";
      console.log("Attempting to fetch URL", url);
      const response = await fetch(url);

      if (!response.ok) throw new Error("Error while fetching core info");

      return response.json();
    },
  });
  const info = data?.data;

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
        {info && (
          <div>
            <p>
              Home Assistant {info.version} running on {info.machine}/
              {info.arch}
            </p>
            <p>
              IP: {info.ip_address}, Port: {info.port}, SSL:{" "}
              {info.ssl ? "Yes" : "No"}
            </p>
            {info.update_available && (
              <p>Update to {info.version_latest} is available!</p>
            )}
          </div>
        )}
      </div>
    </>
  );
}

export default App;
