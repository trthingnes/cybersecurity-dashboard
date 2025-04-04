import { ThumbDown } from "@mui/icons-material"
import { Typography } from "@mui/material"
import { Route, Routes } from "react-router"

import App from "./App"
import { ChecksTab } from "./tabs/ChecksTab"

export const AppRoutes = () => (
    <Routes>
        <Route path="*" element={<App />}>
            <Route index element={<ChecksTab />} />
            <Route
                path="*"
                element={
                    <Typography variant="h1" textAlign="center">
                        This page has not been added yet <ThumbDown />
                    </Typography>
                }
            />
        </Route>
    </Routes>
)
