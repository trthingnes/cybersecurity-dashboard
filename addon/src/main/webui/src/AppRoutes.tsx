import { ThumbDown } from "@mui/icons-material"
import { Stack, Typography } from "@mui/material"
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
                    <Stack textAlign="center" mt={5} spacing={5}>
                        <Typography variant="h1">
                            This page has not been added yet <ThumbDown />
                        </Typography>
                    </Stack>
                }
            />
        </Route>
    </Routes>
)
