import { Launch } from "@mui/icons-material"
import {
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
    const resources = [
        {
            title: "Enable HTTPS using Let's Encrypt in Home Assistant",
            ingress:
                "In this guide, I will demonstrate how to use the Let's Encrypt add-on without using any reverse proxies. The advantage of this approach is that HTTP access is disabled once you enable HTTPS so only encrypted connections are allowed to Home Assistant.",
            url: "https://theprivatesmarthome.com/how-to/enable-https-using-lets-encrypt-in-home-assistant/",
            keywords: ["HTTPS"],
        },
        {
            title: "What is HTTPS?",
            ingress:
                "HTTPS is a secure way to send data between a web server and a web browser.",
            url: "https://www.cloudflare.com/learning/ssl/what-is-https/",
            keywords: ["HTTPS"],
        },
        {
            title: "Home Assistant remote access with Cloudflared",
            ingress:
                "Cloudflared connects your Home Assistant instance via a secure tunnel to a domain or subdomain at Cloudflare. This allows you to expose your Home Assistant instance and other services to the Internet without opening ports on your router.",
            url: "https://github.com/brenner-tobias/addon-cloudflared/blob/main/cloudflared/DOCS.md",
            keywords: ["HTTPS", "Remote Access"],
        },
        {
            title: "Remotely access Home Assistant via Tailscale",
            ingress:
                "In our latest video, we walk through how to remotely access your Home Assistant by adding it to your Tailscale network.",
            url: "https://tailscale.com/blog/remotely-access-home-assistant",
            keywords: ["HTTPS", "Remote Access"],
        },
        {
            title: "Dynamic DNS with DuckDNS",
            ingress:
                "DuckDNS is a free service that allows you to bind your own favorite subdomain under duckdns.org to the public IP address in use from your router, even though such address is dynamically allocated by your internet service provider and therefore changes over time.",
            url: "https://www.home-assistant.io/integrations/duckdns/",
            keywords: ["Remote Access"],
        },
        {
            title: "Backups in Home Assistant",
            ingress:
                "It is important to regularly back up your Home Assistant setup. You may have spent many hours configuring your system and creating automations.",
            url: "https://www.home-assistant.io/common-tasks/general/#backups",
            keywords: ["Backups"],
        },
        {
            title: "Google Drive Backup Integration",
            ingress:
                "This integration allows you to connect your Google Drive with Home Assistant Backups. When you set up this integration, your Google Drive will have a new folder called Home Assistant where all the backups will be stored.",
            url: "https://www.home-assistant.io/integrations/google_drive/",
            keywords: ["Backups"],
        },
        {
            title: "What is a CVE?",
            ingress:
                "CVE, short for Common Vulnerabilities and Exposures, is a list of publicly disclosed computer security flaws. When someone refers to a CVE, they mean a security flaw that's been assigned a CVE ID number.",
            url: "https://www.redhat.com/en/topics/security/what-is-cve",
            keywords: ["Vulnerabilities", "CVE"],
        },
        {
            title: "GitHub Advisory Database",
            ingress:
                "Security vulnerability database inclusive of CVEs and GitHub originated security advisories from the world of open source software. ",
            url: "https://github.com/advisories",
            keywords: ["Vulnerabilities", "CVE"],
        },
        {
            title: "Keeping devices and software up to date",
            ingress:
                "To prevent known vulnerabilities from being exploited, all of this software must be kept up to date. This means installing patches released by the software developers to close security holes found in their products. Hence the name 'patching'.",
            url: "https://www.ncsc.gov.uk/collection/device-security-guidance/managing-deployed-devices/keeping-devices-and-software-up-to-date",
            keywords: ["Updates"],
        },
    ]

    const [params, setParams] = useSearchParams()
    const keywords = useMemo(() => params.getAll("keywords"), [params])
    const resourcesToShow = useMemo(
        () =>
            resources.filter((a) => {
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
                            Learn more about keeping your Home Assistant secure
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
                            {resourcesToShow.length == 0 && (
                                <Typography textAlign="center" width="100%">
                                    There are no resources to display. Consider
                                    removing the filter.
                                </Typography>
                            )}
                            {resourcesToShow.map((a) => (
                                <Grid
                                    size={{ xs: 12, md: 6, lg: 4 }}
                                    key={resources.indexOf(a)}
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
                                                    <Chip key={k} label={k} />
                                                ))}
                                            </Stack>
                                        </CardContent>
                                        <CardActions>
                                            <Button
                                                size="small"
                                                endIcon={<Launch />}
                                                href={a.url}
                                                target="_blank"
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
