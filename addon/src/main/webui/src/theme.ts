import { createTheme } from "@mui/material"

export const theme = createTheme({
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
