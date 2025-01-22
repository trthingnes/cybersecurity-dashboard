// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryOptions, useSuspenseQuery } from "@tanstack/react-query";
import { getApiHomeAssistantCoreInfo, getApiHomeAssistantDumpEnv } from "../requests/services.gen";
import { GetApiHomeAssistantCoreInfoError, GetApiHomeAssistantDumpEnvError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetApiHomeAssistantCoreInfoSuspense = <TData = Common.GetApiHomeAssistantCoreInfoDefaultResponse, TError = GetApiHomeAssistantCoreInfoError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetApiHomeAssistantCoreInfoKeyFn(clientOptions, queryKey), queryFn: () => getApiHomeAssistantCoreInfo({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
export const useGetApiHomeAssistantDumpEnvSuspense = <TData = Common.GetApiHomeAssistantDumpEnvDefaultResponse, TError = GetApiHomeAssistantDumpEnvError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetApiHomeAssistantDumpEnvKeyFn(clientOptions, queryKey), queryFn: () => getApiHomeAssistantDumpEnv({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
