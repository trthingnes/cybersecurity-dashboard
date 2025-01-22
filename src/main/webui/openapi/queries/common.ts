// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiHomeAssistantCoreInfo, getApiHomeAssistantDumpEnv } from "../requests/services.gen";
export type GetApiHomeAssistantCoreInfoDefaultResponse = Awaited<ReturnType<typeof getApiHomeAssistantCoreInfo>>["data"];
export type GetApiHomeAssistantCoreInfoQueryResult<TData = GetApiHomeAssistantCoreInfoDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiHomeAssistantCoreInfoKey = "GetApiHomeAssistantCoreInfo";
export const UseGetApiHomeAssistantCoreInfoKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiHomeAssistantCoreInfoKey, ...(queryKey ?? [clientOptions])];
export type GetApiHomeAssistantDumpEnvDefaultResponse = Awaited<ReturnType<typeof getApiHomeAssistantDumpEnv>>["data"];
export type GetApiHomeAssistantDumpEnvQueryResult<TData = GetApiHomeAssistantDumpEnvDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiHomeAssistantDumpEnvKey = "GetApiHomeAssistantDumpEnv";
export const UseGetApiHomeAssistantDumpEnvKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiHomeAssistantDumpEnvKey, ...(queryKey ?? [clientOptions])];
