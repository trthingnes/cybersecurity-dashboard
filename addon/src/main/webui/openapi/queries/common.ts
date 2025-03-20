// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiReport, postApiCheck } from "../requests/services.gen";
export type GetApiReportDefaultResponse = Awaited<ReturnType<typeof getApiReport>>["data"];
export type GetApiReportQueryResult<TData = GetApiReportDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiReportKey = "GetApiReport";
export const UseGetApiReportKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiReportKey, ...(queryKey ?? [clientOptions])];
export type PostApiCheckMutationResult = Awaited<ReturnType<typeof postApiCheck>>;
export const usePostApiCheckKey = "PostApiCheck";
export const UsePostApiCheckKeyFn = (mutationKey?: Array<unknown>) => [usePostApiCheckKey, ...(mutationKey ?? [])];
