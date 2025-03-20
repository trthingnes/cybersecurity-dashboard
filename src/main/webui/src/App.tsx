import {
    Alert,
    Button,
    CircularProgress,
    Grid2,
    Stack,
    Typography,
} from "@mui/material"

import { useGetApiReport, usePostApiCheck } from "../openapi/queries"
import { CheckResultCard } from "./components/CheckResultCard.tsx"

function App() {
    const { data, isFetching, isError, refetch } = useGetApiReport()
    const { mutateAsync } = usePostApiCheck()

    return (
        <Grid2 container spacing={2} mt={5}>
            <Grid2 size="grow"></Grid2>
            <Grid2 size={{ xs: 12, sm: 8, md: 6, xl: 4 }} justifyItems="center">
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
                        {isFetching && <CircularProgress />}
                        {isError && (
                            <Alert severity="error">
                                An error occured while fetching the
                                cybersecurity report.
                            </Alert>
                        )}
                        {!isFetching &&
                            data?.results
                                ?.sort((a, b) => {
                                    if (a.risk == b.risk) return 0
                                    if (a.risk === "HIGH" || b.risk === "HIGH")
                                        return a.risk === "HIGH" ? -1 : 1
                                    if (
                                        a.risk === "MODERATE" ||
                                        b.risk === "MODERATE"
                                    )
                                        return a.risk === "MODERATE" ? -1 : 1
                                    return 0
                                })
                                .map((r) => (
                                    <CheckResultCard
                                        key={r.name}
                                        result={r}
                                        sx={{ flexGrow: "grow" }}
                                    />
                                ))}
                    </Stack>
                </Stack>
            </Grid2>
            <Grid2 size="grow"></Grid2>
        </Grid2>
    )
}

export default App
