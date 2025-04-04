import { Button, Stack, Typography } from "@mui/material"
import { Route, Routes, useNavigate } from "react-router"

import App from "./App"
import { ChecksTab } from "./tabs/ChecksTab"

export const AppRoutes = () => {
    const navigate = useNavigate()

    return (
        <Routes>
            <Route path="/" element={<App />}>
                <Route path="/" element={<ChecksTab />} />
                <Route
                    path="/analysis"
                    element={<Typography>Checks</Typography>}
                />
            </Route>
            <Route
                path="*"
                element={
                    <Stack textAlign="center" mt={5} spacing={5}>
                        <Typography variant="h1">
                            Yikes! That page does not seem to exist...
                        </Typography>
                        <Typography>
                            <Button onClick={() => navigate("/")}>
                                Take me back
                            </Button>
                        </Typography>
                    </Stack>
                }
            />
        </Routes>
    )
}
