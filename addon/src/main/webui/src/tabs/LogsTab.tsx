import { Autorenew } from "@mui/icons-material"
import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Grid,
    Paper,
    Stack,
    Typography,
} from "@mui/material"

import { useGetApiLogsUnified } from "../../openapi/queries"

export function LogsTab() {
    const { data, isError, isPending, isRefetching, refetch } =
        useGetApiLogsUnified()

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
                    An error occured while fetching the unified logs.
                </Alert>
            </Box>
        )
    }

    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ md: 12, xl: 10 }}>
                {data && (
                    <Stack spacing={8} m="auto">
                        <Stack spacing={4}>
                            <Typography variant="h1" textAlign="center">
                                Unified logs from your Home Assistant
                            </Typography>
                            <Paper>
                                <Stack p={2}>
                                    {data.map((entry, i) => (
                                        <Typography
                                            textOverflow="ellipsis"
                                            overflow="hidden"
                                            key={i}
                                        >
                                            {entry}
                                        </Typography>
                                    ))}
                                    <Button
                                        startIcon={<Autorenew />}
                                        variant="contained"
                                        loading={isRefetching}
                                        onClick={() => refetch()}
                                        sx={{
                                            marginTop: "1rem",
                                        }}
                                    >
                                        Refetch logs
                                    </Button>
                                </Stack>
                            </Paper>
                        </Stack>
                    </Stack>
                )}
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
