// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getHaCoreInfo } from "../requests/services.gen";
export type GetHaCoreInfoDefaultResponse = Awaited<ReturnType<typeof getHaCoreInfo>>["data"];
export type GetHaCoreInfoQueryResult<TData = GetHaCoreInfoDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetHaCoreInfoKey = "GetHaCoreInfo";
export const UseGetHaCoreInfoKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetHaCoreInfoKey, ...(queryKey ?? [clientOptions])];
