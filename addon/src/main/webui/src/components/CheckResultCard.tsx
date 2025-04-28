import { ExpandMore, LocalLibrary, SearchOff } from "@mui/icons-material"
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Box,
    Button,
    Chip,
    Grid,
    Paper,
    PaperProps,
    Stack,
    Typography,
} from "@mui/material"
import { useMemo } from "react"
import { useNavigate } from "react-router"

import {
    usePostApiOverviewCheckByIdDisable,
    usePostApiOverviewCheckByIdEnable,
} from "../../openapi/queries"
import { CheckResult } from "../../openapi/requests/types.gen"
import { capitalize, getColorByRisk } from "../utils"

export function CheckResultCard({
    result,
    onChange,
    isLoading,
    ...props
}: {
    readonly result: CheckResult
    readonly onChange: () => void
    readonly isLoading: boolean
} & PaperProps) {
    const navigate = useNavigate()
    const { mutateAsync: disable } = usePostApiOverviewCheckByIdDisable()
    const { mutateAsync: enable } = usePostApiOverviewCheckByIdEnable()
    const isDisabled = useMemo(() => result.risk === "DISABLED", [result])
    const mutateOptions = useMemo(() => {
        return { path: { id: result.id } }
    }, [result])

    return (
        <Paper>
            <Accordion>
                <AccordionSummary expandIcon={<ExpandMore />}>
                    <Grid
                        container
                        alignItems="center"
                        spacing={2}
                        width="calc(100% - 1rem)"
                        {...props}
                    >
                        <Grid size="grow">
                            <Typography variant="h5" component="h2">
                                {result.title}
                            </Typography>
                            <Typography>{result.summary}</Typography>
                        </Grid>
                        <Grid size="auto">
                            <Chip
                                label={capitalize(result.risk)}
                                color={getColorByRisk(result.risk)}
                            />
                        </Grid>
                    </Grid>
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
                            loading={isLoading}
                            color={isDisabled ? "success" : "error"}
                            sx={{ minWidth: "max-content" }}
                            onClick={() => {
                                ;(isDisabled
                                    ? enable(mutateOptions)
                                    : disable(mutateOptions)
                                ).then(() => onChange())
                            }}
                            endIcon={<SearchOff />}
                        >
                            {result.risk === "DISABLED"
                                ? "Enable check"
                                : "Disable check"}
                        </Button>
                    </Stack>

                    <Stack
                        direction="row"
                        spacing={1}
                        mt={2}
                        alignItems="center"
                        justifyContent="space-between"
                    >
                        <Box>
                            <Typography variant="h6" component="h3">
                                Mitigation
                            </Typography>
                            <Typography>{result.mitigation}</Typography>
                        </Box>
                        <Button
                            variant="outlined"
                            sx={{ minWidth: "max-content" }}
                            onClick={() => {
                                navigate(
                                    `/learn?keywords=${result.keywords.join(",")}`
                                )
                            }}
                            endIcon={<LocalLibrary />}
                        >
                            Learn more
                        </Button>
                    </Stack>
                </AccordionDetails>
            </Accordion>
        </Paper>
    )
}
