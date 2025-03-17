import { useGetApiReport } from "../openapi/queries"
import "./App.css"

function App() {
    const { data, isLoading, isError } = useGetApiReport();

    return (
        <>
            <h1>Cybersecurity Dashboard</h1>
            <div className="card">
                {isLoading && "Loading..."}
                {isError && "Error!"}
                {data && (
                    <div>
                        { data.results.map((result) => <p key={result.check.name}>
                            {result.check.name} reported risk {result.risk} {result.message ? `with message "${result.message}"` : ""}
                        </p>)}
                    </div>
                )}
            </div>
        </>
    )
}

export default App
