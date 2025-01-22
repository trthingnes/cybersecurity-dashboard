// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getHomeassistantCoreInfo } from "../requests/services.gen";
export type GetHomeassistantCoreInfoDefaultResponse = Awaited<ReturnType<typeof getHomeassistantCoreInfo>>["data"];
export type GetHomeassistantCoreInfoQueryResult<TData = GetHomeassistantCoreInfoDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetHomeassistantCoreInfoKey = "GetHomeassistantCoreInfo";
export const UseGetHomeassistantCoreInfoKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetHomeassistantCoreInfoKey, ...(queryKey ?? [clientOptions])];
