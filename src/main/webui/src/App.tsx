import { useGetHaCoreInfo } from "../openapi/queries"
import "./App.css"
import reactLogo from "./assets/react.svg"
import viteLogo from "./assets/vite.svg"

function App() {
    const { data, isLoading, isError } = useGetHaCoreInfo()
    const info = data?.data

    return (
        <>
            <div>
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img
                        src={reactLogo}
                        className="logo react"
                        alt="React logo"
                    />
                </a>
            </div>
            <h1>Cybersecurity Dashboard</h1>
            <div className="card">
                {isLoading && <p>Loading...</p>}
                {isError && <p>Error!</p>}
                {info && (
                    <div>
                        <p>
                            Home Assistant {info.version} running on{" "}
                            {info.machine}/{info.arch}
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
    )
}

export default App
