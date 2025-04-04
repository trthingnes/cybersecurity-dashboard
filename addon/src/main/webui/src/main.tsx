import { CssBaseline, ThemeProvider } from "@mui/material"
import { QueryClient, QueryClientProvider } from "@tanstack/react-query"
import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { MemoryRouter } from "react-router"

import { client } from "../openapi/requests/services.gen.ts"
import { AppRoutes } from "./AppRoutes.tsx"
import { theme } from "./theme.ts"

// Generated Client Config: https://openapi-react-query-codegen.vercel.app/guides/introduction/
const baseUrl =
    window.location.protocol +
    "//" +
    window.location.host +
    window.location.pathname.substring(0, window.location.pathname.length - 1)
client.setConfig({ baseUrl })
console.debug("Communicating with the API using base URL", baseUrl)
const queryClient = new QueryClient()

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={theme}>
                <CssBaseline />
                <MemoryRouter>
                    <AppRoutes />
                </MemoryRouter>
            </ThemeProvider>
        </QueryClientProvider>
    </StrictMode>
)
