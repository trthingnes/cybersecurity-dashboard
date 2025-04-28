import { Launch } from "@mui/icons-material"
import {
    Badge,
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    Chip,
    Grid,
    Stack,
    Typography,
} from "@mui/material"
import { useMemo } from "react"
import { useSearchParams } from "react-router"

export function LearnTab() {
    const articles = [
        {
            title: "Enable HTTPS using Let’s Encrypt in Home Assistant",
            ingress:
                "In this guide, I will demonstrate how to use the Let’s Encrypt add-on without using any reverse proxies. The advantage of this approach is that HTTP access is disabled once you enable HTTPS so only encrypted connections are allowed to Home Assistant.",
            url: "https://theprivatesmarthome.com/how-to/enable-https-using-lets-encrypt-in-home-assistant/",
            keywords: ["HTTPS", "Let's Encrypt"],
        },
    ]

    const [params, setParams] = useSearchParams()
    const keywords = useMemo(() => params.getAll("keywords"), [params])
    const articlesToShow = useMemo(
        () =>
            articles.filter((a) => {
                if (keywords.length == 0) {
                    return true
                }

                return (
                    a.keywords.findIndex((k) => keywords.indexOf(k) != -1) != -1
                )
            }),
        // eslint-disable-next-line react-hooks/exhaustive-deps
        [keywords]
    )

    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ sm: 12, lg: 10, xl: 8 }}>
                <Stack spacing={8} m="auto">
                    <Stack spacing={4}>
                        <Typography variant="h1" textAlign="center">
                            Learn about cybersecurity in Home Assistant
                            {keywords.length > 0 && (
                                <Typography mt={1}>
                                    Showing resources related to{" "}
                                    {keywords.map((s) => `"${s}"`).join(", ")}
                                    <Button
                                        onClick={() => setParams()}
                                        sx={{ marginLeft: 1 }}
                                    >
                                        Clear filter
                                    </Button>
                                </Typography>
                            )}
                        </Typography>
                        <Grid container spacing={2}>
                            {articlesToShow.map((a) => (
                                <Grid
                                    size={{ xs: 12, md: 6, lg: 4 }}
                                    key={articles.indexOf(a)}
                                >
                                    <Card>
                                        <CardContent>
                                            <Typography
                                                gutterBottom
                                                variant="h5"
                                                component="div"
                                            >
                                                {a.title}
                                            </Typography>
                                            <Typography
                                                variant="body2"
                                                sx={{
                                                    color: "text.secondary",
                                                }}
                                            >
                                                {a.ingress}
                                            </Typography>
                                            <Stack
                                                direction="row"
                                                spacing={1}
                                                mt={2}
                                            >
                                                {a.keywords.map((k) => (
                                                    <Chip
                                                        key={k}
                                                        label={k}
                                                    ></Chip>
                                                ))}
                                            </Stack>
                                        </CardContent>
                                        <CardActions>
                                            <Button
                                                size="small"
                                                endIcon={<Launch />}
                                                href={a.url}
                                            >
                                                Read more
                                            </Button>
                                        </CardActions>
                                    </Card>
                                </Grid>
                            ))}
                        </Grid>
                    </Stack>
                </Stack>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
