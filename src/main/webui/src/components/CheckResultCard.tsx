import { Chip, Grid2, Paper, Typography } from "@mui/material"

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

export function CheckResultCard({ result }: { readonly result: CheckResult }) {
    return (
        <Paper>
            <Grid2
                m={2}
                container
                spacing={2}
                alignItems="center"
                minWidth="40rem"
            >
                <Grid2 size="grow">
                    <Typography variant="h6" component="h2">
                        {result.check.name}
                        <Typography>{result.message}</Typography>
                    </Typography>
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
