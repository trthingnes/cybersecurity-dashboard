// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryOptions, useSuspenseQuery } from "@tanstack/react-query";
import { getApiOverview } from "../requests/services.gen";
import { GetApiOverviewError } from "../requests/types.gen";
import * as Common from "./common";
export const useGetApiOverviewSuspense = <TData = Common.GetApiOverviewDefaultResponse, TError = GetApiOverviewError, TQueryKey extends Array<unknown> = unknown[]>(clientOptions: Options<unknown, true> = {}, queryKey?: TQueryKey, options?: Omit<UseQueryOptions<TData, TError>, "queryKey" | "queryFn">) => useSuspenseQuery<TData, TError>({ queryKey: Common.UseGetApiOverviewKeyFn(clientOptions, queryKey), queryFn: () => getApiOverview({ ...clientOptions }).then(response => response.data as TData) as TData, ...options });
