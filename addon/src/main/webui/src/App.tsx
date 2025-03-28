import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Grid2,
    Stack,
    Typography,
} from "@mui/material"
import { useMemo, useState } from "react"

import { useGetApiReport } from "../openapi/queries"
import { CheckResultCard } from "./components/CheckResultCard.tsx"
import { sortResultsByRisk } from "./utils.ts"

function App() {
    const { data, isPending, isError, isRefetching, refetch } =
        useGetApiReport()

    const [showMore, setShowMore] = useState(false)
    const results = useMemo(() => {
        return (
            data?.results
                ?.sort((a, b) => a.title.localeCompare(b.title))
                .sort(sortResultsByRisk) ?? []
        )
    }, [data])

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
                            loading={isRefetching}
                            onClick={async () => {
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
                        {isPending && (
                            <Box sx={{ margin: "auto !important" }}>
                                <CircularProgress />
                            </Box>
                        )}

                        {isError && (
                            <Alert severity="error">
                                An error occured while fetching the
                                cybersecurity report.
                            </Alert>
                        )}
                        {!isPending &&
                            results
                                .filter(
                                    (r) =>
                                        ["HIGH", "MODERATE"].includes(r.risk) ||
                                        showMore
                                )
                                .map((r) => (
                                    <CheckResultCard
                                        key={r.title}
                                        result={r}
                                        sx={{ flexGrow: "grow" }}
                                    />
                                ))}
                        {!isPending && (
                            <Button onClick={() => setShowMore(!showMore)}>
                                {showMore ? "Show less" : "Show more"}
                            </Button>
                        )}
                    </Stack>
                </Stack>
            </Grid2>
            <Grid2 size="grow"></Grid2>
        </Grid2>
    )
}

export default App
