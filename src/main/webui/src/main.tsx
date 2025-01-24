import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { StrictMode } from "react"
import { createRoot } from "react-dom/client"

import { client } from "../openapi/requests/services.gen.ts"
import App from "./App.tsx"
import "./index.css"

// Generated Client Config: https://openapi-react-query-codegen.vercel.app/guides/introduction/
const baseUrl =
    window.location.protocol +
    "//" +
    window.location.host +
    window.location.pathname.substring(0, window.location.pathname.length - 1)
console.debug("Setting query client base to", baseUrl)
client.setConfig({ baseUrl })

const queryClient = new QueryClient()

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <QueryClientProvider client={queryClient}>
            <App />
        </QueryClientProvider>
    </StrictMode>
)
