import { CheckResult, Risk, Tier } from "../openapi/requests/types.gen"

export function capitalize(string: string) {
    return string.toLowerCase().replace(/^./, string[0].toUpperCase())
}

export function lowercase(string: string) {
    return string.toLowerCase()
}

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

export function getColorByRisk(risk: Risk) {
    if (risk === "LOW") return "success"
    if (risk === "MODERATE") return "warning"
    if (risk === "HIGH") return "error"

    return "default"
}

export function getHtmlColorByTier(tier: Tier) {
    if (tier == "GOLD") return "gold"
    if (tier == "SILVER") return "silver"
    if (tier == "BRONZE") return "#CD7F32"
}
