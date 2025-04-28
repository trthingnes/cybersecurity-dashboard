// Based on https://mui.com/material-ui/react-progress/
import { WorkspacePremium } from "@mui/icons-material"
import {
    Box,
    CircularProgress,
    CircularProgressProps,
    Stack,
    Typography,
} from "@mui/material"

import { Tier } from "../../openapi/requests/types.gen"
import { getHtmlColorByTier } from "../utils"

export function CircularProgressWithTier(
    props: CircularProgressProps & { size: number; value: number; tier: Tier }
) {
    return (
        <Box
            sx={{
                position: "relative",
                display: "inline-flex",
                justifyContent: "center",
            }}
        >
            <CircularProgress
                variant="determinate"
                sx={{ color: getHtmlColorByTier(props.tier) }}
                {...props}
            />
            <Stack
                sx={{
                    top: 0,
                    left: 0,
                    bottom: 0,
                    right: 0,
                    position: "absolute",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                }}
            >
                {props.tier != "NONE" && (
                    <WorkspacePremium
                        htmlColor={getHtmlColorByTier(props.tier)}
                        sx={{ fontSize: props.size * 0.5 }}
                    />
                )}

                <Typography fontSize={props.tier == "NONE" ? "2rem" : "1rem"}>
                    {Math.round(props.value)}%
                </Typography>
            </Stack>
        </Box>
    )
}
