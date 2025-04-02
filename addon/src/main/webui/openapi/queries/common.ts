// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiReport, postApiChecksByIdDisable, postApiChecksByIdEnable, postApiReportGenerate } from "../requests/services.gen";
export type GetApiReportDefaultResponse = Awaited<ReturnType<typeof getApiReport>>["data"];
export type GetApiReportQueryResult<TData = GetApiReportDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiReportKey = "GetApiReport";
export const UseGetApiReportKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiReportKey, ...(queryKey ?? [clientOptions])];
export type PostApiChecksByIdDisableMutationResult = Awaited<ReturnType<typeof postApiChecksByIdDisable>>;
export const usePostApiChecksByIdDisableKey = "PostApiChecksByIdDisable";
export const UsePostApiChecksByIdDisableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiChecksByIdDisableKey, ...(mutationKey ?? [])];
export type PostApiChecksByIdEnableMutationResult = Awaited<ReturnType<typeof postApiChecksByIdEnable>>;
export const usePostApiChecksByIdEnableKey = "PostApiChecksByIdEnable";
export const UsePostApiChecksByIdEnableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiChecksByIdEnableKey, ...(mutationKey ?? [])];
export type PostApiReportGenerateMutationResult = Awaited<ReturnType<typeof postApiReportGenerate>>;
export const usePostApiReportGenerateKey = "PostApiReportGenerate";
export const UsePostApiReportGenerateKeyFn = (mutationKey?: Array<unknown>) => [usePostApiReportGenerateKey, ...(mutationKey ?? [])];
