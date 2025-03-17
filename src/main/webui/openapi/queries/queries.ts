// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { useMutation, UseMutationOptions, useQuery, UseQueryOptions } from "@tanstack/react-query";
import { getApiReport, postApiCheck } from "../requests/services.gen";
import { GetApiReportError, PostApiCheckError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetApiReport = <TData = Common.GetApiReportDefaultResponse, TError = GetApiReportError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useQuery<TData, TError>({ queryKey: Common.UseGetApiReportKeyFn(clientOptions, queryKey), queryFn: () => getApiReport({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
export const usePostApiCheck = <TData = Common.PostApiCheckMutationResult, TError = PostApiCheckError, TQueryKey extends Array<unknown> = unknown[], TContext = unknown>(mutationKey?: TQueryKey, options?: Omit<UseMutationOptions<TData, TError, Options<unknown, true>, TContext>, "mutationKey" | "mutationFn">) => useMutation<TData, TError, Options<unknown, true>, TContext>({ mutationKey: Common.UsePostApiCheckKeyFn(mutationKey), mutationFn: clientOptions => postApiCheck(clientOptions) as unknown as Promise<TData>, ...options });
