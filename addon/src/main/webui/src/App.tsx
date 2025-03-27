import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Grid2,
    Stack,
    Typography,
} from "@mui/material"
import { useState } from "react"

import { useGetApiReport, usePostApiCheck } from "../openapi/queries"
import { CheckResultCard } from "./components/CheckResultCard.tsx"

function App() {
    const { data, isFetching, isError, refetch } = useGetApiReport()
    const { mutateAsync, isPending } = usePostApiCheck()

    const [showLowRisk, setShowLowRisk] = useState(false)

    return (
        <Grid2 container spacing={2} m={2} mt={5} mb={5}>
            <Grid2 size="grow"></Grid2>
            <Grid2
                size={{ xs: 12, sm: 10, md: 8, xl: 6 }}
                justifyItems="center"
            >
                <Stack spacing={6} m="auto">
                    <Typography variant="h1" align="center">
                        Cybersecurity Dashboard
                    </Typography>
                    <Stack
                        direction="row"
                        spacing={2}
                        alignItems="center"
                        justifyContent="center"
                        useFlexGap
                        sx={{ flexWrap: "wrap" }}
                    >
                        <Button
                            variant="contained"
                            onClick={async () => {
                                await mutateAsync({})
                                refetch()
                            }}
                        >
                            Check Now
                        </Button>
                        {data?.results && (
                            <Typography>
                                Last updated{" "}
                                {new Date(data.timestamp).toLocaleString()}
                            </Typography>
                        )}
                    </Stack>
                    <Stack spacing={2} m={1}>
                        {isPending ||
                            (isFetching && (
                                <Box sx={{ margin: "auto !important" }}>
                                    <CircularProgress />
                                </Box>
                            ))}

                        {isError && (
                            <Alert severity="error">
                                An error occured while fetching the
                                cybersecurity report.
                            </Alert>
                        )}
                        {!isFetching &&
                            data?.results
                                ?.sort((a, b) => a.title.localeCompare(b.title))
                                .sort((a, b) => {
                                    if (a.risk == b.risk) return 0
                                    if (a.risk === "HIGH" || b.risk === "HIGH")
                                        return a.risk === "HIGH" ? -1 : 1
                                    if (
                                        a.risk === "MODERATE" ||
                                        b.risk === "MODERATE"
                                    )
                                        return a.risk === "MODERATE" ? -1 : 1
                                    if (a.risk === "LOW" || b.risk === "LOW")
                                        return a.risk === "LOW" ? -1 : 1
                                    return 0
                                })
                                .filter((r) => r.risk != "LOW" || showLowRisk)
                                .map((r) => (
                                    <CheckResultCard
                                        key={r.title}
                                        result={r}
                                        sx={{ flexGrow: "grow" }}
                                    />
                                ))}
                    </Stack>
                    <Button onClick={() => setShowLowRisk(!showLowRisk)}>
                        {showLowRisk
                            ? "Hide low risk checks"
                            : "Show all checks"}
                    </Button>
                </Stack>
            </Grid2>
            <Grid2 size="grow"></Grid2>
        </Grid2>
    )
}

export default App
