import { useGetApiInfo, useGetApiPublicIp } from "../openapi/queries"
import "./App.css"
import reactLogo from "./assets/react.svg"
import viteLogo from "./assets/vite.svg"

function App() {
    const infoQuery = useGetApiInfo()
    const ipQuery = useGetApiPublicIp()

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
                {[infoQuery, ipQuery].some(q => q.isLoading) && <p>Loading...</p>}
                {[infoQuery, ipQuery].some(q => q.isError) && <p>Error!</p>}
                {infoQuery && (
                    <div>
                        <p>
                            Home Assistant {infoQuery.data?.data?.version} running on{" "}
                            {infoQuery.data?.data?.machine}/{infoQuery.data?.data?.arch}
                        </p>
                        <p>
                            Public IP: {ipQuery.data}, Private IP: {infoQuery.data?.data?.ip_address}, Port: {infoQuery.data?.data?.port}, SSL:{" "}
                            {infoQuery.data?.data?.ssl ? "Yes" : "No"}
                        </p>
                        {infoQuery.data?.data?.update_available && (
                            <p>Update to {infoQuery.data?.data?.version_latest} is available!</p>
                        )}
                    </div>
                )}
            </div>
        </>
    )
}

export default App
