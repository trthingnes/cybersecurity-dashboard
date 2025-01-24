// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { useQuery, UseQueryOptions } from "@tanstack/react-query";
import { getHaCoreInfo, getHaDumpEnv } from "../requests/services.gen";
import { GetHaCoreInfoError, GetHaDumpEnvError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetHaCoreInfo = <TData = Common.GetHaCoreInfoDefaultResponse, TError = GetHaCoreInfoError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useQuery<TData, TError>({ queryKey: Common.UseGetHaCoreInfoKeyFn(clientOptions, queryKey), queryFn: () => getHaCoreInfo({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
export const useGetHaDumpEnv = <TData = Common.GetHaDumpEnvDefaultResponse, TError = GetHaDumpEnvError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useQuery<TData, TError>({ queryKey: Common.UseGetHaDumpEnvKeyFn(clientOptions, queryKey), queryFn: () => getHaDumpEnv({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
