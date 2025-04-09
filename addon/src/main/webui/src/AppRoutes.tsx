import { ThumbDown } from "@mui/icons-material"
import { Typography } from "@mui/material"
import { Route, Routes } from "react-router"

import App from "./App"
import { AccessTab } from "./tabs/AccessTab"
import { ChecksTab } from "./tabs/ChecksTab"
import { CommunityTab } from "./tabs/CommunityTab"
import { DevicesTab } from "./tabs/DevicesTab"
import { LearnTab } from "./tabs/LearnTab"
import { LogsTab } from "./tabs/LogsTab"

export const AppRoutes = () => (
    <Routes>
        <Route path="*" element={<App />}>
            <Route index element={<ChecksTab />} />
            <Route path="devices" element={<DevicesTab />} />
            <Route path="logs" element={<LogsTab />} />
            <Route path="access" element={<AccessTab />} />
            <Route path="community" element={<CommunityTab />} />
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
