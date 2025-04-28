import {
    Air,
    CleaningServices,
    HotTub,
    Lightbulb,
    Lock,
    MusicVideo,
    NotificationsActive,
    Opacity,
    Plumbing,
    RollerShades,
    Security,
    Thermostat,
    Videocam,
} from "@mui/icons-material"
import {
    Alert,
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Paper,
    Stack,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
    capitalize,
} from "@mui/material"

import { useGetApiDevices } from "../../openapi/queries"

export function DevicesTab() {
    const { data, isPending, isError } = useGetApiDevices()

    const icons = {
        alarm_control_panel: <Security />,
        camera: <Videocam />,
        climate: <Thermostat />,
        cover: <RollerShades />,
        fan: <Air />,
        humidifier: <Opacity />,
        light: <Lightbulb />,
        lock: <Lock />,
        media_player: <MusicVideo />,
        remote: <MusicVideo />,
        siren: <NotificationsActive />,
        vacuum: <CleaningServices />,
        valve: <Plumbing />,
        water_heater: <HotTub />,
    }

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
                    An error occured while fetching device overview.
                </Alert>
            </Box>
        )
    }

    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ sm: 12, lg: 10, xl: 8 }}>
                {data && (
                    <Stack spacing={8} m="auto">
                        <Stack spacing={4}>
                            <Typography variant="h1" textAlign="center">
                                You have {data.length} devices connected to Home
                                Assistant
                            </Typography>
                            <Grid container spacing={2}>
                                {data.map((device, i) => (
                                    <Grid
                                        size={{ xs: 12, sm: 6, md: 4, lg: 3 }}
                                        key={device.entity_id}
                                    >
                                        <Paper sx={{ position: "relative" }}>
                                            {i < 3 && (
                                                <Chip
                                                    color="success"
                                                    label="New"
                                                    sx={{
                                                        position: "absolute",
                                                        top: "1rem",
                                                        right: "1rem",
                                                    }}
                                                />
                                            )}
                                            {i == 7 && (
                                                <Chip
                                                    color="warning"
                                                    label="Unused"
                                                    sx={{
                                                        position: "absolute",
                                                        top: "1rem",
                                                        right: "1rem",
                                                    }}
                                                />
                                            )}
                                            <Stack
                                                spacing={1}
                                                alignItems="center"
                                                p={2}
                                            >
                                                <>
                                                    {
                                                        // @ts-expect-error Using string to index object.
                                                        icons[
                                                            device.entity_id.split(
                                                                "."
                                                            )[0]
                                                        ]
                                                    }
                                                    <Typography variant="body1">
                                                        {device.attributes
                                                            .friendly_name ??
                                                            device.entity_id}
                                                    </Typography>
                                                </>
                                                <TableContainer>
                                                    <Table size="small">
                                                        <TableRow>
                                                            <TableCell variant="head">
                                                                Integration
                                                            </TableCell>
                                                            <TableCell>
                                                                Demo
                                                            </TableCell>
                                                        </TableRow>
                                                        <TableRow>
                                                            <TableCell variant="head">
                                                                Device Model
                                                            </TableCell>
                                                            <TableCell>
                                                                Unknown
                                                            </TableCell>
                                                        </TableRow>
                                                        <TableRow>
                                                            <TableCell variant="head">
                                                                Installed
                                                                Firmware
                                                            </TableCell>
                                                            <TableCell>
                                                                Unknown
                                                            </TableCell>
                                                        </TableRow>
                                                        <TableRow>
                                                            <TableCell variant="head">
                                                                Latest Firmware
                                                            </TableCell>
                                                            <TableCell>
                                                                Unknown
                                                            </TableCell>
                                                        </TableRow>
                                                    </Table>
                                                </TableContainer>
                                                <Button>View device</Button>
                                            </Stack>
                                        </Paper>
                                    </Grid>
                                ))}
                            </Grid>
                        </Stack>
                    </Stack>
                )}
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
