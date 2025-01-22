// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { useQuery, UseQueryOptions } from "@tanstack/react-query";
import { getApiHomeAssistantCoreInfo, getApiHomeAssistantDumpEnv } from "../requests/services.gen";
import { GetApiHomeAssistantCoreInfoError, GetApiHomeAssistantDumpEnvError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetApiHomeAssistantCoreInfo = <TData = Common.GetApiHomeAssistantCoreInfoDefaultResponse, TError = GetApiHomeAssistantCoreInfoError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useQuery<TData, TError>({ queryKey: Common.UseGetApiHomeAssistantCoreInfoKeyFn(clientOptions, queryKey), queryFn: () => getApiHomeAssistantCoreInfo({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
export const useGetApiHomeAssistantDumpEnv = <TData = Common.GetApiHomeAssistantDumpEnvDefaultResponse, TError = GetApiHomeAssistantDumpEnvError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useQuery<TData, TError>({ queryKey: Common.UseGetApiHomeAssistantDumpEnvKeyFn(clientOptions, queryKey), queryFn: () => getApiHomeAssistantDumpEnv({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
