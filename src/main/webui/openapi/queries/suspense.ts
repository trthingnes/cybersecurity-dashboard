// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryOptions, useSuspenseQuery } from "@tanstack/react-query";
import { getApiInfo, getApiPublicIp } from "../requests/services.gen";
import { GetApiInfoError, GetApiPublicIpError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetApiInfoSuspense = <TData = Common.GetApiInfoDefaultResponse, TError = GetApiInfoError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetApiInfoKeyFn(clientOptions, queryKey), queryFn: () => getApiInfo({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
export const useGetApiPublicIpSuspense = <TData = Common.GetApiPublicIpDefaultResponse, TError = GetApiPublicIpError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetApiPublicIpKeyFn(clientOptions, queryKey), queryFn: () => getApiPublicIp({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
