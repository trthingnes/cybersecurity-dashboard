import { useGetApiReport, usePostApiCheck } from "../openapi/queries"
import "./App.css"

function App() {
    const { data, isLoading, isError, refetch } = useGetApiReport()
    const { mutateAsync } = usePostApiCheck()

    return (
        <>
            <h1>Cybersecurity Dashboard</h1>
            <button
                onClick={async () => {
                    await mutateAsync({})
                    refetch()
                }}
            >
                Check Now
            </button>
            <div className="card">
                {isLoading && "Loading..."}
                {isError && "Error!"}
                {data?.results && (
                    <div>
                        <b>Last updated {new Date(data.timestamp).toLocaleString()}</b>
                        {data.results.map((result) => (
                            <p key={result.check.name}>
                                {result.check.name} reported risk {result.risk}{" "}
                                {result.message
                                    ? `with message "${result.message}"`
                                    : ""}
                            </p>
                        ))}
                    </div>
                )}
            </div>
        </>
    )
}

export default App
