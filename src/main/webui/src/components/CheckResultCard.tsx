import { HelpOutline } from "@mui/icons-material"
import {
    Chip,
    Grid2,
    Paper,
    PaperProps,
    Stack,
    Tooltip,
    Typography,
} from "@mui/material"

import { CheckResult, Risk } from "../../openapi/requests/types.gen"

function getLabel(risk: Risk) {
    if (risk === "LOW") return "Low"
    if (risk === "MODERATE") return "Moderate"
    if (risk === "HIGH") return "High"
}

function getColor(risk: Risk) {
    if (risk === "LOW") return "success"
    if (risk === "MODERATE") return "warning"
    if (risk === "HIGH") return "error"
}

export function CheckResultCard({
    result,
    ...props
}: {
    readonly result: CheckResult
} & PaperProps) {
    return (
        <Paper>
            <Grid2 m={2} container spacing={2} alignItems="center" {...props}>
                <Grid2 size="grow">
                    <Stack direction="row" spacing={1} alignItems="center">
                        <Typography variant="h6" component="h2">
                            {result.name}{" "}
                        </Typography>
                        <Tooltip title={result.description}>
                            <HelpOutline />
                        </Tooltip>
                    </Stack>

                    <Typography>{result.message}</Typography>
                </Grid2>
                <Grid2 size="auto">
                    <Chip
                        label={getLabel(result.risk)}
                        color={getColor(result.risk)}
                    />
                </Grid2>
            </Grid2>
        </Paper>
    )
}
