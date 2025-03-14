// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiInfo, getApiPublicIp } from "../requests/services.gen";
export type GetApiInfoDefaultResponse = Awaited<ReturnType<typeof getApiInfo>>["data"];
export type GetApiInfoQueryResult<TData = GetApiInfoDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiInfoKey = "GetApiInfo";
export const UseGetApiInfoKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiInfoKey, ...(queryKey ?? [clientOptions])];
export type GetApiPublicIpDefaultResponse = Awaited<ReturnType<typeof getApiPublicIp>>["data"];
export type GetApiPublicIpQueryResult<TData = GetApiPublicIpDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiPublicIpKey = "GetApiPublicIp";
export const UseGetApiPublicIpKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiPublicIpKey, ...(queryKey ?? [clientOptions])];
