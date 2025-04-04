import { Box, Grid, Tab, Tabs } from "@mui/material"
import { Outlet, useLocation, useNavigate } from "react-router"

function App() {
    const navigate = useNavigate()
    const { pathname } = useLocation()
    return (
        <Grid container m={2}>
            <Grid size="grow"></Grid>
            <Grid size={{ xs: 12, sm: 10, md: 8, xl: 6 }} justifyItems="center">
                <Tabs
                    centered
                    value={pathname}
                    onChange={(_, v) => navigate(v)}
                >
                    <Tab label="Common Risk Checks" value="/" />
                    <Tab label="Data Overview" value="/analysis" />
                </Tabs>
                <Box mt={5}>
                    <Outlet />
                </Box>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}

export default App
