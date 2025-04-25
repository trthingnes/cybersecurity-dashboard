import {
    BroadcastOnHome,
    Checklist,
    Description,
    LocalLibrary,
} from "@mui/icons-material"
import { Box, Grid, Tab, Tabs } from "@mui/material"
import { Outlet, useLocation, useNavigate } from "react-router"

function App() {
    const navigate = useNavigate()
    const { pathname } = useLocation()
    return (
        <>
            <Grid container justifyContent="center">
                <Tabs
                    value={pathname}
                    variant="scrollable"
                    scrollButtons="auto"
                    onChange={(_, v) => navigate(v)}
                >
                    <Tab label="Overview" icon={<Checklist />} value="/" />
                    <Tab label="Logs" icon={<Description />} value="/logs" />
                    <Tab
                        label="Devices"
                        icon={<BroadcastOnHome />}
                        value="/devices"
                    />
                    <Tab label="Learn" icon={<LocalLibrary />} value="/learn" />
                    {/*
                    <Tab label="Access" icon={<LockOpen />} value="/access" />
                    <Tab
                        label="Community"
                        icon={<People />}
                        value="/community"
                    />
                    */}
                </Tabs>
            </Grid>
            <Box>
                <Outlet />
            </Box>
        </>
    )
}

export default App
