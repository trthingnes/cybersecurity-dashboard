// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryOptions, useSuspenseQuery } from "@tanstack/react-query";
import { getHaCoreInfo } from "../requests/services.gen";
import { GetHaCoreInfoError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetHaCoreInfoSuspense = <TData = Common.GetHaCoreInfoDefaultResponse, TError = GetHaCoreInfoError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetHaCoreInfoKeyFn(clientOptions, queryKey), queryFn: () => getHaCoreInfo({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
