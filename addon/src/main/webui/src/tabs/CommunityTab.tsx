import { Grid, Stack, Typography } from "@mui/material"

export function CommunityTab() {
    return (
        <Grid container m={3} mt={4}>
            <Grid size="grow"></Grid>
            <Grid size={{ xs: 12, sm: 10, md: 8, xl: 6 }}>
                <Stack spacing={8} m="auto">
                    <Stack spacing={6}>
                        <Typography variant="h1" textAlign="center">
                            Information based on other users of Home Assistant
                            <Typography>This is a placeholder tab.</Typography>
                        </Typography>
                        <Stack spacing={1}>
                            <Typography variant="h2" textAlign="center">
                                Top 10 Cybersecurity Risks
                            </Typography>
                            <Typography>
                                Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Integer in orci ut elit
                                consectetur dapibus a nec quam. Phasellus eget
                                ultricies elit, sit amet ullamcorper ligula.
                                Mauris eget urna bibendum, molestie erat ut,
                                porta leo. Maecenas in lectus metus. Etiam
                                commodo imperdiet turpis, non porttitor elit
                                sodales vitae. Quisque id augue et massa
                                ultricies suscipit id vitae lorem. Nullam a
                                tincidunt mauris. Donec ornare vehicula sapien
                                vel vehicula. Vivamus gravida vel lacus ac
                                commodo. Nullam ante turpis, imperdiet eu mi
                                nec, aliquam efficitur ligula. Integer eget elit
                                ullamcorper, auctor lacus eget, imperdiet enim.
                                Nam vitae lacinia tellus, at suscipit erat.
                                Quisque lorem nisi, rutrum sit amet tortor sed,
                                consequat molestie lorem. Duis ultrices arcu ac
                                nibh tempor tristique. Interdum et malesuada
                                fames ac ante ipsum primis in faucibus.
                            </Typography>
                        </Stack>
                        <Stack spacing={1}>
                            <Typography variant="h2" textAlign="center">
                                Third-party Extension Ratings
                            </Typography>
                            <Typography>
                                Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit. Integer in orci ut elit
                                consectetur dapibus a nec quam. Phasellus eget
                                ultricies elit, sit amet ullamcorper ligula.
                                Mauris eget urna bibendum, molestie erat ut,
                                porta leo. Maecenas in lectus metus. Etiam
                                commodo imperdiet turpis, non porttitor elit
                                sodales vitae. Quisque id augue et massa
                                ultricies suscipit id vitae lorem. Nullam a
                                tincidunt mauris. Donec ornare vehicula sapien
                                vel vehicula. Vivamus gravida vel lacus ac
                                commodo. Nullam ante turpis, imperdiet eu mi
                                nec, aliquam efficitur ligula. Integer eget elit
                                ullamcorper, auctor lacus eget, imperdiet enim.
                                Nam vitae lacinia tellus, at suscipit erat.
                                Quisque lorem nisi, rutrum sit amet tortor sed,
                                consequat molestie lorem. Duis ultrices arcu ac
                                nibh tempor tristique. Interdum et malesuada
                                fames ac ante ipsum primis in faucibus.
                            </Typography>
                        </Stack>
                    </Stack>
                </Stack>
            </Grid>
            <Grid size="grow"></Grid>
        </Grid>
    )
}
