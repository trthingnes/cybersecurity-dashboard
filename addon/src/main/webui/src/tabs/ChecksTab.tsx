import { Autorenew } from "@mui/icons-material"
import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Grid,
    Stack,
    Typography,
} from "@mui/material"
import { useMemo, useState } from "react"

import {
    useGetApiOverview,
    usePostApiOverviewGenerate,
} from "../../openapi/queries"
import { CheckResultCard } from "../components/CheckResultCard.tsx"
import { CircularProgressWithTier } from "../components/CircularProgressWithTier.tsx"
import { lowercase, sortResultsByRisk, splitResultsByRisk } from "../utils.ts"

export function ChecksTab() {
    const { data, isError, isPending, refetch } = useGetApiOverview()
    const { mutateAsync: generate, isPending: isGenerating } =
        usePostApiOverviewGenerate()
    const update = () => generate({}).then(() => refetch())
    const [showMore, setShowMore] = useState(false)
    const updated = useMemo(
        () => (data?.timestamp ? new Date(data.timestamp) : null),
        [data]
    )
    const results = useMemo(() => {
        if (!data) return []

        return data.results
            .sort((a, b) => a.title.localeCompare(b.title))
            .sort(sortResultsByRisk)
    }, [data])
    const { significantResults, otherResults } = useMemo(
        () => splitResultsByRisk(results),
        [results]
    )

    if (isPending) {
        return (
            <Box textAlign="center" mt={4}>
                <CircularProgress size="4rem" />
            </Box>
        )
    }

    if (isError) {
        return (
            <Box m={4}>
                <Alert
                    severity="error"
                    sx={{ width: "max-content", margin: "auto !important" }}
                >
                    An error occured while fetching the cybersecurity report.
                </Alert>
            </Box>
        )
    }

    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ xs: 12, sm: 10, md: 8, xl: 6 }}>
                {data && (
                    <Stack spacing={8} m="auto">
                        <Stack spacing={4}>
                            <Typography variant="h1" textAlign="center">
                                Your Home Assistant's cybersecurity is{" "}
                                {data.tier == "NONE" ? (
                                    "untiered"
                                ) : (
                                    <>{lowercase(data.tier)} tier</>
                                )}
                            </Typography>
                            <CircularProgressWithTier
                                size={200}
                                tier={data.tier}
                                value={Math.round(100 * data.completion!)}
                            />
                            <Stack spacing={2}>
                                {data.tier != "GOLD" && (
                                    <Typography textAlign="center">
                                        Mitigate {data.tierAdvanceIn}{" "}
                                        {data.tier == "NONE" ? "high" : ""} risk
                                        {data.tierAdvanceIn! > 1 ? "s" : ""} to
                                        improve your instance's cybersecurity
                                        tier.
                                    </Typography>
                                )}
                                <Stack
                                    direction="row"
                                    spacing={2}
                                    alignItems="center"
                                    justifyContent="center"
                                    useFlexGap
                                    sx={{ flexWrap: "wrap" }}
                                >
                                    <Button
                                        startIcon={<Autorenew />}
                                        variant="contained"
                                        loading={isGenerating}
                                        onClick={update}
                                    >
                                        Check Now
                                    </Button>
                                    {updated && (
                                        <Typography>
                                            Last updated{" "}
                                            {updated.toLocaleString()}
                                        </Typography>
                                    )}
                                </Stack>
                            </Stack>
                        </Stack>
                        <Stack spacing={2}>
                            <Typography variant="h2" textAlign="center">
                                Potential risks to your system
                            </Typography>
                            {significantResults.length == 0 && (
                                <Typography textAlign="center">
                                    There are no significant risks to display!
                                </Typography>
                            )}
                            {significantResults.length > 0 && (
                                <Box>
                                    {significantResults.map((r) => (
                                        <CheckResultCard
                                            key={r.title}
                                            result={r}
                                            onChange={update}
                                            isLoading={isGenerating}
                                            sx={{ flexGrow: "grow" }}
                                        />
                                    ))}
                                </Box>
                            )}
                            <Button onClick={() => setShowMore(!showMore)}>
                                {showMore ? "Show less" : "Show more"}
                            </Button>
                            {showMore && (
                                <Box>
                                    {otherResults.map((r) => (
                                        <CheckResultCard
                                            key={r.title}
                                            result={r}
                                            onChange={update}
                                            isLoading={isGenerating}
                                            sx={{ flexGrow: "grow" }}
                                        />
                                    ))}
                                </Box>
                            )}
                        </Stack>
                    </Stack>
                )}
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
