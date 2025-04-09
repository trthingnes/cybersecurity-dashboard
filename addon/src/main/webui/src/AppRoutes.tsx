import { ThumbDown } from "@mui/icons-material"
import { Typography } from "@mui/material"
import { Route, Routes } from "react-router"

import App from "./App"
import { ChecksTab } from "./tabs/ChecksTab"
import { DevicesTab } from "./tabs/DevicesTab"
import { LogsTab } from "./tabs/LogsTab"
import { LearnTab } from "./tabs/LearnTab"

export const AppRoutes = () => (
    <Routes>
        <Route path="*" element={<App />}>
            <Route index element={<ChecksTab />} />
            <Route path="devices" element={<DevicesTab />} />
            <Route path="logs" element={<LogsTab />} />
            <Route path="learn" element={<LearnTab />} />
            <Route
                path="*"
                element={
                    <Typography p={4} variant="h1" textAlign="center">
                        This page has not been added yet <ThumbDown />
                    </Typography>
                }
            />
        </Route>
    </Routes>
)
