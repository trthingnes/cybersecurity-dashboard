import { CssBaseline, ThemeProvider, createTheme } from "@mui/material"
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
client.setConfig({ baseUrl })

const queryClient = new QueryClient()

const theme = createTheme({
    palette: {
        mode: "dark",
        primary: {
            main: "#0aa9f0",
        },
        secondary: {
            main: "#ff9800",
        },
    },
    typography: {
        h1: {
            fontSize: "32px",
            fontWeight: "400",
        },
        h2: {
            fontSize: "24px",
            fontWeight: "400",
        },
        h5: {
            fontSize: "1.25rem",
            fontWeight: "600",
            lineHeight: "1.6",
        },
        h6: {
            fontSize: "1.05rem",
            fontWeight: "600",
            lineHeight: "1.4",
        },
    },
})

createRoot(document.getElementById("root")!).render(
    <StrictMode>
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={theme}>
                <CssBaseline />
                <App />
            </ThemeProvider>
        </QueryClientProvider>
    </StrictMode>
)
