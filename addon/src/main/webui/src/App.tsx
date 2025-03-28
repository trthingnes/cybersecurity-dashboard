import { Celebration } from "@mui/icons-material"
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
import { sortResultsByRisk, splitResultsByRisk } from "./utils.ts"

function App() {
    const { data, isError, isPending, isRefetching, refetch } =
        useGetApiReport()

    const [showMore, setShowMore] = useState(false)

    const updated = useMemo(
        () => (data?.timestamp ? new Date(data.timestamp) : null),
        [data]
    )
    const results = useMemo(() => {
        if (!data) return []
        else
            return data.results
                .sort((a, b) => a.title.localeCompare(b.title))
                .sort(sortResultsByRisk)
    }, [data])
    const { significantResults, otherResults } = useMemo(
        () => splitResultsByRisk(results),
        [results]
    )

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
                            onClick={() => {
                                refetch()
                            }}
                        >
                            Check Now
                        </Button>
                        {updated && (
                            <Typography>
                                Last updated {updated.toLocaleString()}
                            </Typography>
                        )}
                    </Stack>
                    <Stack spacing={5} m={1}>
                        {isError && (
                            <Alert severity="error">
                                An error occured while fetching the
                                cybersecurity report.
                            </Alert>
                        )}
                        {isPending && (
                            <Box sx={{ margin: "auto !important" }}>
                                <CircularProgress />
                            </Box>
                        )}
                        {!isPending && significantResults.length == 0 && (
                            <Typography variant="h5" textAlign="center">
                                Congrats! No significant risks were discovered{" "}
                                <Celebration />
                            </Typography>
                        )}
                        {significantResults.length > 0 && (
                            <>
                                <Typography variant="h5" textAlign="center">
                                    We found risks that require your attention!
                                </Typography>
                                <Box>
                                    {significantResults.map((r) => (
                                        <CheckResultCard
                                            key={r.title}
                                            result={r}
                                            refetch={() => refetch()}
                                            isRefetching={isRefetching}
                                            sx={{ flexGrow: "grow" }}
                                        />
                                    ))}
                                </Box>
                            </>
                        )}

                        {showMore && (
                            <Box>
                                {otherResults.map((r) => (
                                    <CheckResultCard
                                        key={r.title}
                                        result={r}
                                        refetch={() => refetch()}
                                        isRefetching={isRefetching}
                                        sx={{ flexGrow: "grow" }}
                                    />
                                ))}
                            </Box>
                        )}
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
