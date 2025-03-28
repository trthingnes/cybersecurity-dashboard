import { ExpandMore } from "@mui/icons-material"
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Box,
    Button,
    Chip,
    Grid2,
    Paper,
    PaperProps,
    Stack,
    Typography,
} from "@mui/material"
import { useMemo, useState } from "react"

import {
    usePostApiChecksByIdDisable,
    usePostApiChecksByIdEnable,
} from "../../openapi/queries"
import { CheckResult } from "../../openapi/requests/types.gen"
import { getColorByRisk, getLabelByRisk } from "../utils"

export function CheckResultCard({
    result,
    refetch,
    isRefetching,
    ...props
}: {
    readonly result: CheckResult
    readonly refetch: () => void
    readonly isRefetching: boolean
} & PaperProps) {
    const { mutate: enable } = usePostApiChecksByIdEnable()
    const { mutate: disable } = usePostApiChecksByIdDisable()
    const isDisabled = useMemo(() => result.risk === "DISABLED", [result])
    const mutateOptions = useMemo(() => {
        return { path: { id: result.id } }
    }, [result])

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
                                label={getLabelByRisk(result.risk)}
                                color={getColorByRisk(result.risk)}
                            />
                        </Grid2>
                    </Grid2>
                </AccordionSummary>
                <AccordionDetails>
                    <Stack
                        direction="row"
                        spacing={1}
                        alignItems="center"
                        justifyContent="space-between"
                    >
                        <Box>
                            <Typography variant="h6" component="h3">
                                Description
                            </Typography>
                            <Typography>{result.description}</Typography>
                        </Box>
                        <Button
                            variant="outlined"
                            loading={isRefetching}
                            color={isDisabled ? "success" : "error"}
                            sx={{ minWidth: "max-content" }}
                            onClick={() => {
                                if (isDisabled) enable(mutateOptions)
                                else disable(mutateOptions)
                                refetch()
                            }}
                        >
                            {result.risk === "DISABLED"
                                ? "Enable check"
                                : "Disable check"}
                        </Button>
                    </Stack>

                    {["HIGH", "MODERATE"].includes(result.risk) && (
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
