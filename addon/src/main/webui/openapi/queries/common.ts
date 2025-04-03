// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiReport, postApiCheckByIdDisable, postApiCheckByIdEnable, postApiReportGenerate } from "../requests/services.gen";
export type GetApiReportDefaultResponse = Awaited<ReturnType<typeof getApiReport>>["data"];
export type GetApiReportQueryResult<TData = GetApiReportDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiReportKey = "GetApiReport";
export const UseGetApiReportKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiReportKey, ...(queryKey ?? [clientOptions])];
export type PostApiCheckByIdDisableMutationResult = Awaited<ReturnType<typeof postApiCheckByIdDisable>>;
export const usePostApiCheckByIdDisableKey = "PostApiCheckByIdDisable";
export const UsePostApiCheckByIdDisableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiCheckByIdDisableKey, ...(mutationKey ?? [])];
export type PostApiCheckByIdEnableMutationResult = Awaited<ReturnType<typeof postApiCheckByIdEnable>>;
export const usePostApiCheckByIdEnableKey = "PostApiCheckByIdEnable";
export const UsePostApiCheckByIdEnableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiCheckByIdEnableKey, ...(mutationKey ?? [])];
export type PostApiReportGenerateMutationResult = Awaited<ReturnType<typeof postApiReportGenerate>>;
export const usePostApiReportGenerateKey = "PostApiReportGenerate";
export const UsePostApiReportGenerateKeyFn = (mutationKey?: Array<unknown>) => [usePostApiReportGenerateKey, ...(mutationKey ?? [])];
