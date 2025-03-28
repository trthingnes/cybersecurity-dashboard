import { CheckResult, Risk } from "../openapi/requests/types.gen"

export function sortResultsByRisk(a: CheckResult, b: CheckResult) {
    if (a.risk == b.risk) return 0
    if (a.risk === "HIGH" || b.risk === "HIGH")
        return a.risk === "HIGH" ? -1 : 1
    if (a.risk === "MODERATE" || b.risk === "MODERATE")
        return a.risk === "MODERATE" ? -1 : 1
    if (a.risk === "LOW" || b.risk === "LOW") return a.risk === "LOW" ? -1 : 1
    if (a.risk === "UNKNOWN" || b.risk === "UNKNOWN")
        return a.risk === "UNKNOWN" ? -1 : 1
    return 0
}

export function splitResultsByRisk(results: CheckResult[]) {
    return {
        significantResults: results.filter((r) =>
            ["HIGH", "MODERATE"].includes(r.risk)
        ),
        otherResults: results.filter(
            (r) => !["HIGH", "MODERATE"].includes(r.risk)
        ),
    }
}

export function getLabelByRisk(risk: Risk) {
    return risk.toLowerCase().replace(/^./, risk[0].toUpperCase())
}

export function getColorByRisk(risk: Risk) {
    if (risk === "LOW") return "success"
    if (risk === "MODERATE") return "warning"
    if (risk === "HIGH") return "error"

    return "default"
}
