import { ExpandMore } from "@mui/icons-material"
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Chip,
    Grid2,
    Paper,
    PaperProps,
    Typography,
} from "@mui/material"

import { CheckResult, Risk } from "../../openapi/requests/types.gen"

function getLabel(risk: Risk) {
    if (risk === "UNKNOWN") return "Unknown"
    if (risk === "LOW") return "Low"
    if (risk === "MODERATE") return "Moderate"
    if (risk === "HIGH") return "High"
}

function getColor(risk: Risk) {
    if (risk === "UNKNOWN") return "default"
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
            <Accordion>
                <AccordionSummary expandIcon={<ExpandMore />}>
                    <Grid2
                        container
                        alignItems="center"
                        spacing={2}
                        width="calc(100% - 1rem)"
                        {...props}
                    >
                        <Grid2 size="grow">
                            <Typography variant="h5" component="h2">
                                {result.title}
                            </Typography>
                            <Typography>{result.summary}</Typography>
                        </Grid2>
                        <Grid2 size="auto">
                            <Chip
                                label={getLabel(result.risk)}
                                color={getColor(result.risk)}
                            />
                        </Grid2>
                    </Grid2>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography variant="h6" component="h3">
                        Description
                    </Typography>
                    <Typography>{result.description}</Typography>
                    {result.risk != "UNKNOWN" && result.risk != "LOW" && (
                        <>
                            <Typography variant="h6" component="h3" mt={2}>
                                Mitigation
                            </Typography>
                            <Typography>{result.mitigation}</Typography>
                        </>
                    )}
                </AccordionDetails>
            </Accordion>
        </Paper>
    )
}
