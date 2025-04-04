import {
    Checklist,
    Description,
    LocalLibrary,
    LockOpen,
    People,
} from "@mui/icons-material"
import { Box, Grid, Tab, Tabs } from "@mui/material"
import { Outlet, useLocation, useNavigate } from "react-router"

function App() {
    const navigate = useNavigate()
    const { pathname } = useLocation()
    return (
        <Grid container m={1}>
            <Grid size="grow"></Grid>
            <Grid size={{ xs: 12, sm: 10, md: 8, xl: 6 }} justifyItems="center">
                <Tabs
                    centered
                    value={pathname}
                    onChange={(_, v) => navigate(v)}
                >
                    <Tab label="Overview" icon={<Checklist />} value="/" />
                    <Tab label="Logs" icon={<Description />} value="/logs" />
                    <Tab label="Access" icon={<LockOpen />} value="/access" />
                    <Tab
                        label="Community"
                        icon={<People />}
                        value="/community"
                    />
                    <Tab label="Learn" icon={<LocalLibrary />} value="/learn" />
                </Tabs>
                <Box mt={3}>
                    <Outlet />
                </Box>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}

export default App
