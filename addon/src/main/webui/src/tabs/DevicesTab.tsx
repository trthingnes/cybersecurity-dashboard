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
    ToggleOn,
    Videocam,
} from "@mui/icons-material"
import {
    Alert,
    Box,
    CircularProgress,
    Grid,
    Paper,
    Stack,
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
        switch: <ToggleOn />,
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
                                {data.map((device) => (
                                    <Grid
                                        size={{ xs: 12, sm: 6, md: 4, lg: 3 }}
                                        key={device.entity_id}
                                    >
                                        <Paper>
                                            <Stack p={1} alignItems="center">
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
                                                <Typography variant="body2">
                                                    {capitalize(device.state)}
                                                </Typography>
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
