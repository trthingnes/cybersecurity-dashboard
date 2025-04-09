import { Card, CardContent, Grid, Stack, Typography } from "@mui/material"

export function AccessTab() {
    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ xs: 12, sm: 10, md: 8, xl: 6 }}>
                <Stack spacing={8} m="auto">
                    <Stack spacing={4}>
                        <Typography variant="h1" textAlign="center">
                            Access control in your Home Assistant
                            <Typography>This is a placeholder tab.</Typography>
                        </Typography>
                        <Card>
                            <CardContent>
                                <Typography
                                    gutterBottom
                                    variant="h5"
                                    component="div"
                                >
                                    What can users access?
                                </Typography>
                                <Typography
                                    variant="body2"
                                    sx={{
                                        color: "text.secondary",
                                    }}
                                >
                                    Lorem ipsum dolor sit amet, consectetur
                                    adipiscing elit. Pellentesque in sodales
                                    felis. Quisque fringilla lectus ex, in
                                    commodo nulla scelerisque.
                                </Typography>
                                <Typography>
                                    <ul style={{ marginBottom: 0 }}>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                    </ul>
                                </Typography>
                            </CardContent>
                        </Card>
                        <Card>
                            <CardContent>
                                <Typography
                                    gutterBottom
                                    variant="h5"
                                    component="div"
                                >
                                    What can integrations and devices access?
                                </Typography>
                                <Typography
                                    variant="body2"
                                    sx={{
                                        color: "text.secondary",
                                    }}
                                >
                                    Lorem ipsum dolor sit amet, consectetur
                                    adipiscing elit. Pellentesque in sodales
                                    felis. Quisque fringilla lectus ex, in
                                    commodo nulla scelerisque.
                                </Typography>
                                <Typography>
                                    <ul style={{ marginBottom: 0 }}>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                    </ul>
                                </Typography>
                            </CardContent>
                        </Card>
                        <Card>
                            <CardContent>
                                <Typography
                                    gutterBottom
                                    variant="h5"
                                    component="div"
                                >
                                    What can my addons access?
                                </Typography>
                                <Typography
                                    variant="body2"
                                    sx={{
                                        color: "text.secondary",
                                    }}
                                >
                                    Lorem ipsum dolor sit amet, consectetur
                                    adipiscing elit. Pellentesque in sodales
                                    felis. Quisque fringilla lectus ex, in
                                    commodo nulla scelerisque.
                                </Typography>
                                <Typography>
                                    <ul style={{ marginBottom: 0 }}>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                        <li>
                                            Lorem ipsum dolor sit amet,
                                            consectetur adipiscing elit.
                                        </li>
                                    </ul>
                                </Typography>
                            </CardContent>
                        </Card>
                    </Stack>
                </Stack>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
