import { Launch } from "@mui/icons-material"
import {
    Button,
    Card,
    CardActions,
    CardContent,
    Grid,
    Stack,
    Typography,
} from "@mui/material"

export function LearnTab() {
    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ sm: 12, lg: 10, xl: 8 }}>
                <Stack spacing={8} m="auto">
                    <Stack spacing={4}>
                        <Typography variant="h1" textAlign="center">
                            Learn about cybersecurity in Home Assistant
                            <Typography>This is a placeholder tab.</Typography>
                        </Typography>
                        <Grid container spacing={2}>
                            {Array.from({ length: 10 }, (_, i) => i + 1).map(
                                (number) => (
                                    <Grid
                                        size={{ xs: 12, md: 6, lg: 4 }}
                                        key={number}
                                    >
                                        <Card>
                                            <CardContent>
                                                <Typography
                                                    gutterBottom
                                                    variant="h5"
                                                    component="div"
                                                >
                                                    Post {number}
                                                </Typography>
                                                <Typography
                                                    variant="body2"
                                                    sx={{
                                                        color: "text.secondary",
                                                    }}
                                                >
                                                    Lorem ipsum dolor sit amet,
                                                    consectetur adipiscing elit.
                                                    Pellentesque in sodales
                                                    felis. Quisque fringilla
                                                    lectus ex, in commodo nulla
                                                    scelerisque.
                                                </Typography>
                                            </CardContent>
                                            <CardActions>
                                                <Button
                                                    size="small"
                                                    endIcon={<Launch />}
                                                >
                                                    Read more
                                                </Button>
                                            </CardActions>
                                        </Card>
                                    </Grid>
                                )
                            )}
                        </Grid>
                    </Stack>
                </Stack>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
